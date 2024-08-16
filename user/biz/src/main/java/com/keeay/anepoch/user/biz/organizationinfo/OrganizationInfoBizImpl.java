
package com.keeay.anepoch.user.biz.organizationinfo;

import com.google.common.base.Strings;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.menuinfo.bo.MenuInfoBo;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.organizationinfo.bo.*;
import com.keeay.anepoch.user.service.service.organizationinfo.OrganizationInfoService;
import com.keeay.anepoch.user.biz.organizationinfo.converter.OrganizationInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author AI Admin
 */
@Service
@Slf4j
public class OrganizationInfoBizImpl implements OrganizationInfoBiz {
    private static final String ORGANIZATION_TREE_HEAD_NODE_FLAG = "-1";
    @Resource
    private OrganizationInfoService organizationInfoService;

    /**
     * 新增 record
     *
     * @param addOrganizationInfoBo addOrganizationInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(OrganizationInfoBo addOrganizationInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addOrganizationInfoBo), "addOrganizationInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                OrganizationInfo newOrganizationInfo = OrganizationInfoBoConverter.convertToOrganizationInfo(addOrganizationInfoBo);
                if (!StringUtils.equalsIgnoreCase(newOrganizationInfo.getParentCode(), ORGANIZATION_TREE_HEAD_NODE_FLAG)) {
                    newOrganizationInfo.setCode(newOrganizationInfo.getParentCode() + Strings.padStart(newOrganizationInfo.getCode(), 2, '0'));
                }
                organizationInfoService.insert(newOrganizationInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editOrganizationInfoBo editOrganizationInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(OrganizationInfoBo editOrganizationInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editOrganizationInfoBo), "editOrganizationInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editOrganizationInfoBo.getId()), "editOrganizationInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                OrganizationInfo oldOrganizationInfo = organizationInfoService.getDetailById(editOrganizationInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldOrganizationInfo), "oldOrganizationInfo is null");
                //修改记录
                OrganizationInfo waitToUpdate = OrganizationInfoBoConverter.convertToOrganizationInfo(editOrganizationInfoBo);
                organizationInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryOrganizationInfoBo queryOrganizationInfoBo
     * @return record list
     */
    @Override
    public List<OrganizationInfoBo> getTreeList(OrganizationInfoBo queryOrganizationInfoBo) {
        return new BaseBizTemplate<List<OrganizationInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryOrganizationInfoBo), "queryOrganizationInfoBo is null");
            }

            @Override
            protected List<OrganizationInfoBo> process() {
                OrganizationInfo organizationInfoQuery = OrganizationInfoBoConverter.convertToOrganizationInfo(queryOrganizationInfoBo);
                List<OrganizationInfo> fromDbList = organizationInfoService.list(organizationInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                //寻找根节点
                //树化
                return getTreeList(fromDbList);
            }
        }.execute();
    }

    private List<OrganizationInfoBo> getTreeList(List<OrganizationInfo> targetList) {
        //分组
        Map<String, List<OrganizationInfoBo>> parentGroupMap = targetList.stream()
                .map(data -> JsonMoreUtils.toBean(JsonMoreUtils.toJson(data), OrganizationInfoBo.class))
                .collect(Collectors.groupingBy(OrganizationInfoBo::getParentCode));
        //获取头/根结点
        List<OrganizationInfoBo> rootNodeList = parentGroupMap.get(ORGANIZATION_TREE_HEAD_NODE_FLAG);
        if (CollectionUtils.isEmpty(rootNodeList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        //递归fill菜单数据
        fillChildren(rootNodeList, parentGroupMap);
        return rootNodeList;
    }

    public void fillChildren(List<OrganizationInfoBo> rootNodeList, Map<String, List<OrganizationInfoBo>> parentGroupMap) {
        //寻找children
        for (OrganizationInfoBo currentNode : rootNodeList) {
            //获取当前节点的子节点
            List<OrganizationInfoBo> children = parentGroupMap.get(currentNode.getCode());
            if (CollectionUtils.isEmpty(children)) {
//                currentNode.setChildren(Lists.newArrayList());
                continue;
            }
            currentNode.setChildren(children);
            //fill children
            this.fillChildren(children, parentGroupMap);
        }
    }

    /**
     * 查询record
     *
     * @param recordId recordId
     * @return record orElse null
     */
    @Override
    public OrganizationInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<OrganizationInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected OrganizationInfoBo process() {
                OrganizationInfo fromDb = organizationInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), OrganizationInfoBo.class);
            }
        }.execute();
    }

    /**
     * 通过codes 获取 list
     *
     * @param organizationCodeList organizationCodeList
     * @return data list
     */
    @Override
    public List<OrganizationInfoBo> fetchListByCodes(List<String> organizationCodeList) {
        log.info("fetchListByCodes biz start , organizationCodeList : {}", organizationCodeList);
        return new BaseBizTemplate<List<OrganizationInfoBo>>() {
            @Override
            protected List<OrganizationInfoBo> process() {
                List<OrganizationInfo> listFromDb = organizationInfoService.getListByCodes(organizationCodeList);
                if (CollectionUtils.isEmpty(listFromDb)) {
                    return Lists.newArrayListWithCapacity(0);
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(listFromDb), OrganizationInfoBo.class);
            }
        }.execute();
    }

    /**
     * 通过code删除数据
     *
     * @param code code
     * @return success true orElse false
     */
    @Override
    public Boolean removeByCode(String code) {
        log.info("removeByCode biz start, code : {}", code);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(StringUtils.isNotBlank(code), "code is blank");
            }

            @Override
            protected Boolean process() {
                return organizationInfoService.deleteByCode(code);
            }
        }.execute();
    }
}

