/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.company;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.GeoPoint;

/**
 * Holds companydata.
 *
 * @author ham
 */
public class CompanyDataDTO implements Serializable
{

    private static final long serialVersionUID = -6550030919773234742L;

    private final Integer companyId;

    private String administrativeTenant;
    private String name;
    private String nameAffix;
    private String marketingName;
    private Collection<CompanyBrandDataDTO> brands;
    private String uidNumber;
    private String sapNumber;
    private String companyNumber;
    private Collection<CompanyNumberDataDTO> additionalNumbers;
    private String street;
    private String city;
    private String zip;
    private String countryCode;
    private String country;
    private String region;
    private String iban;
    private String bic;
    private Collection<String> types;
    private String phoneNumber;
    private String speedDial;
    private String faxNumber;
    private String email;
    private String homepage;
    private String postal;
    private Collection<String> legalForm;
    private String dvrNumber;
    private String fbNumber;
    private String certificateType;
    private String certificateNumber;
    private String jurisdiction;
    private GeoPoint location;
    private Collection<CompanyExternalBrandDataDTO> extenalBrands;
    private Integer headquaterCompanyId;
    private LocalDateTime lastUpdate;

    public CompanyDataDTO(@JsonProperty("companyId") Integer companyId)
    {
        super();

        this.companyId = companyId;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(String administrativeTenant)
    {
        this.administrativeTenant = administrativeTenant;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameAffix()
    {
        return nameAffix;
    }

    public void setNameAffix(String nameAffix)
    {
        this.nameAffix = nameAffix;
    }

    public String getMarketingName()
    {
        return marketingName;
    }

    public void setMarketingName(String marketingName)
    {
        this.marketingName = marketingName;
    }

    public Collection<CompanyBrandDataDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<CompanyBrandDataDTO> brands)
    {
        this.brands = brands;
    }

    public String getUidNumber()
    {
        return uidNumber;
    }

    public void setUidNumber(String uidNumber)
    {
        this.uidNumber = uidNumber;
    }

    public String getSapNumber()
    {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber)
    {
        this.sapNumber = sapNumber;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber)
    {
        this.companyNumber = companyNumber;
    }

    public Collection<CompanyNumberDataDTO> getAdditionalNumbers()
    {
        return additionalNumbers;
    }

    public void setAdditionalNumbers(Collection<CompanyNumberDataDTO> additionalNumbers)
    {
        this.additionalNumbers = additionalNumbers;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    public String getBic()
    {
        return bic;
    }

    public void setBic(String bic)
    {
        this.bic = bic;
    }

    public Collection<String> getTypes()
    {
        return types;
    }

    public void setTypes(Collection<String> types)
    {
        this.types = types;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getSpeedDial()
    {
        return speedDial;
    }

    public void setSpeedDial(String speedDial)
    {
        this.speedDial = speedDial;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber)
    {
        this.faxNumber = faxNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getHomepage()
    {
        return homepage;
    }

    public void setHomepage(String homepage)
    {
        this.homepage = homepage;
    }

    public String getPostal()
    {
        return postal;
    }

    public void setPostal(String postal)
    {
        this.postal = postal;
    }

    public Collection<String> getLegalForm()
    {
        return legalForm;
    }

    public void setLegalForm(Collection<String> legalForm)
    {
        this.legalForm = legalForm;
    }

    public String getDvrNumber()
    {
        return dvrNumber;
    }

    public void setDvrNumber(String dvrNumber)
    {
        this.dvrNumber = dvrNumber;
    }

    public String getFbNumber()
    {
        return fbNumber;
    }

    public void setFbNumber(String fbNumber)
    {
        this.fbNumber = fbNumber;
    }

    public String getCertificateType()
    {
        return certificateType;
    }

    public void setCertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber()
    {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber)
    {
        this.certificateNumber = certificateNumber;
    }

    public String getJurisdiction()
    {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction)
    {
        this.jurisdiction = jurisdiction;
    }

    public GeoPoint getLocation()
    {
        return location;
    }

    public void setLocation(GeoPoint location)
    {
        this.location = location;
    }

    public Collection<CompanyExternalBrandDataDTO> getExtenalBrands()
    {
        return extenalBrands;
    }

    public void setExtenalBrands(Collection<CompanyExternalBrandDataDTO> extenalBrands)
    {
        this.extenalBrands = extenalBrands;
    }

    public Integer getHeadquaterCompanyId()
    {
        return headquaterCompanyId;
    }

    public void setHeadquaterCompanyId(Integer headquaterCompanyId)
    {
        this.headquaterCompanyId = headquaterCompanyId;
    }

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format(
            "CompanyDataDTO [companyId=%s, administrativeTenant=%s, name=%s, nameAffix=%s, marketingName=%s, "
                + "uidNumber=%s, sapNumber=%s, companyNumber=%s, additionalNumbers=%s, street=%s, city=%s, zip=%s, "
                + "countryCode=%s, country=%s, region=%s, iban=%s, bic=%s, types=%s, phoneNumber=%s, "
                + "speedDial=%s, faxNumber=%s, email=%s, homepage=%s, postal=%s, legalForm=%s, dvrNumber=%s, fbNumber=%s, "
                + "certificateType=%s, certificateNumber=%s, jurisdiction=%s, location=%s, "
                + "extenalBrands=%s, headquaterCompanyId=%s, lastUpdate=%s]",
            companyId, administrativeTenant, name, nameAffix, marketingName, uidNumber, sapNumber, companyNumber,
            additionalNumbers, street, city, zip, countryCode, country, region, iban, bic, types, phoneNumber,
            speedDial, faxNumber, email, homepage, postal, legalForm, dvrNumber, fbNumber, certificateType,
            certificateNumber, jurisdiction, location, extenalBrands, headquaterCompanyId, lastUpdate);
    }

}