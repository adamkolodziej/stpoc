package com.hybris.showcase.cecs.ymarketingintegration.services;

import com.sap.wec.adtreco.bo.ADTUserIdProvider;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import org.apache.commons.lang.StringUtils;

/**
 * CECS-505 yMarketing - retrieve initiatives over category and email
 *
 * Created by miroslaw.szot@sap.com on 2015-08-05.
 */
public class TwitterADTUserIdProvider extends ADTUserIdProvider {

    @Override
    public String getADTUserId(UserModel user) {
        if( user instanceof CustomerModel ) {
            final String twitterUserProfileId = ((CustomerModel) user).getTwitterUserProfileId();
            if(StringUtils.isNotBlank(twitterUserProfileId)) {
                return twitterUserProfileId;
            }
        }
        return super.getADTUserId(user);
    }
}
