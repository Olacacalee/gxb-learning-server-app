package com.gxb.sites.api.resource.asset.dao;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.domain.other.Asset;
import com.gxb.sites.api.annotation.LcmsRepository;

import java.util.List;

/**
 * Created by Administrator on 2016-10-14.
 */
@LcmsRepository
public interface AssetDao extends BasicDao<Asset> {
    List<Asset> listAssetByVidVtypeType(Asset asset);

    int updateAsset(Asset asset);
}
