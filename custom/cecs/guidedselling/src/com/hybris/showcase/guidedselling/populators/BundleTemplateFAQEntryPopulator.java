package com.hybris.showcase.guidedselling.populators;

import com.hybris.showcase.guidedselling.data.FAQEntryData;
import com.hybris.showcase.guidedselling.model.FAQEntryModel;
import de.hybris.platform.configurablebundlefacades.data.BundleTemplateData;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgolubovic on 20.3.2015..
 */
public class BundleTemplateFAQEntryPopulator<SOURCE extends BundleTemplateModel, TARGET extends BundleTemplateData> implements Populator<SOURCE, TARGET>
{
    public void populate(SOURCE source, TARGET target)
    {
        final List<FAQEntryModel> faqEntriesModel = source.getFaqEntries();
        final List<FAQEntryData> faqEntriesData = new ArrayList<>();
        for(FAQEntryModel faqEntryModel : faqEntriesModel)
        {
            final FAQEntryData faqEntry = new FAQEntryData();
            faqEntry.setCode(faqEntryModel.getCode());
            faqEntry.setAnswer(faqEntryModel.getAnswer());
            faqEntry.setQuestion(faqEntryModel.getQuestion());
            faqEntriesData.add(faqEntry);
        }
        target.setFaqEntries(faqEntriesData);
    }
}