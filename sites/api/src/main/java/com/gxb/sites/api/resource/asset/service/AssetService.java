package com.gxb.sites.api.resource.asset.service;

import com.gxb.modules.domain.other.Asset;
import com.gxb.modules.domain.user.User;
import com.gxb.modules.utils.CollectionTools;
import com.gxb.modules.utils.StringTools;
import com.gxb.sites.api.config.ApiConfig;
import com.gxb.sites.api.resource.asset.dao.AssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@Service
public class AssetService {

    @Autowired
    private ApiConfig apiConfig;
    @Autowired
    private AssetDao assetDao;

    public Asset userResetLink(Long userId, String link) {
        boolean result = false;
        Asset asset = new Asset();
        asset.setLink(link);
        asset.setViewableId(userId);
        asset.setViewableType(Asset.ViewableType.User.toString());
        asset.setType(Asset.Type.Avatar.toString());
        List<Asset> assets = assetDao.listAssetByVidVtypeType(asset);
        if (CollectionTools.isNotEmpty(assets)) {
            result = assetDao.updateAsset(asset) > 0;
        } else {
            if (assetDao.save(asset) > 0) {
                result = true;
            } else {
                result = false;
            }
        }
        if (result) {
            asset.setLink(apiConfig.getUserImage() + link);
            return asset;
        } else {
            return null;
        }
    }

    public void setUserAvatar(User user){
        if (user!=null){
            Asset asset = getAssetByVidVtypeType(user.getUserId(), Asset.ViewableType.User.toString(),
                    Asset.Type.Avatar.toString());
            if(asset != null){
                if(StringTools.isNotBlank(asset.getLink())){
                    user.setAvatarUrl(apiConfig.getUserAvatarLink() + asset.getLink());
                }
            }
        }

    }

    public Asset getAssetByVidVtypeType(long viewableId, String viewableType, String type) {
        Asset assetByCondition = new Asset();
        assetByCondition.setType(type);
        assetByCondition.setViewableId(viewableId);
        assetByCondition.setViewableType(viewableType);
        List<Asset> assets = assetDao.listAssetByVidVtypeType(assetByCondition);
        return assets.size() > 0 ? assets.get(0) : null;
    }
}
