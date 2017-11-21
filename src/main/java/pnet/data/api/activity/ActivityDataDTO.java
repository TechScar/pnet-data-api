package pnet.data.api.activity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.companytype.CompanyTypeLinkBasedMultiTenancy;
import pnet.data.api.util.Traceable;
import pnet.data.api.util.Utils;

/**
 * Holds an activity.
 *
 * @author ham
 */
public class ActivityDataDTO implements CompanyTypeLinkBasedMultiTenancy, Traceable
{

    private ActivityMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<ActivityBrandLinkDTO> brands;
    private Collection<ActivityCompanyTypeLinkDTO> companyTypes;
    private Collection<ActivityContractTypeLinkDTO> contractTypes;
    private Collection<ActivityInfoareaLinkDTO> infoareas;
    private LocalDateTime lastUpdate;

    public ActivityDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the item. The key is the same on all environments.
     */
    public ActivityMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(ActivityMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A map of strings by locale holding the label of the item in multiple languages.
     */
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    public String getLabel(Locale language)
    {
        return Utils.getText(language, labels);
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    /**
     * @return A map of strings by locale holding the description of the item in multiple languages.
     */
    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    /**
     * @param language the language, may be null
     * @return The description in the specified language, null if not found.
     */
    public String getDescriptions(Locale language)
    {
        return Utils.getText(language, descriptions);
    }

    public void setDescriptions(Map<Locale, String> descriptions)
    {
        this.descriptions = descriptions;
    }

    /**
     * @return This activity is only available, if the company has one of these brands.
     */
    public Collection<ActivityBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<ActivityBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    /**
     * @return This activity is only available, if the company has one of these types.
     */
    @Override
    public Collection<ActivityCompanyTypeLinkDTO> getCompanyTypes()
    {
        return companyTypes;
    }

    public void setCompanyTypes(Collection<ActivityCompanyTypeLinkDTO> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This activity is only available, if the company has one of these contracts. This collection is only
     *         relevant, if the company type of the company says so.
     */
    public Collection<ActivityContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<ActivityContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
    }

    /**
     * @return The inforareas, that are linked to this activity.
     */
    public Collection<ActivityInfoareaLinkDTO> getInfoareas()
    {
        return infoareas;
    }

    public void setInfoareas(Collection<ActivityInfoareaLinkDTO> infoareas)
    {
        this.infoareas = infoareas;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

}
