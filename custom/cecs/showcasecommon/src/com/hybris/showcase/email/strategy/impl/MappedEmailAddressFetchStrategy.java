package com.hybris.showcase.email.strategy.impl;

import de.hybris.platform.acceleratorservices.email.strategy.impl.DefaultEmailAddressFetchStrategy;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class MappedEmailAddressFetchStrategy extends DefaultEmailAddressFetchStrategy {

    private static final String MAPPED_EMAIL_PROPERTY_PREFIX = "map.email.";
    private static final String AT_SIGN_PLACEHOLDER = "_at_";

    private static final Logger LOG = Logger.getLogger(MappedEmailAddressFetchStrategy.class);

    private ConfigurationService configurationService;

    @Override
    public EmailAddressModel fetch(final String emailAddress, final String displayName) {
        final String mappedEmailAddress = getMappedEmailAddress(emailAddress);

        if (!StringUtils.equals(emailAddress, mappedEmailAddress)) {
            LOG.info(String.format("Destination email address was changed from %s to %s.", emailAddress, mappedEmailAddress));
        }

        return super.fetch(mappedEmailAddress, displayName);
    }

    private String getMappedEmailAddress(final String emailAddress) {
        final String mappedEmailProperty = MAPPED_EMAIL_PROPERTY_PREFIX + emailAddress.replace("@", AT_SIGN_PLACEHOLDER);
        return getConfigurationService().getConfiguration().getString(mappedEmailProperty, emailAddress);
    }

    protected ConfigurationService getConfigurationService() {
        return configurationService;
    }

    @Required
    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
