/**
 * 
 */
package com.hybris.campaigns.wizard;

import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;



/**
 * @author wojciech.piotrowiak
 *
 */
public interface PostWizardStrategy
{

	public abstract void afterWizardAction(Wizard wizard, WizardPage page);

}