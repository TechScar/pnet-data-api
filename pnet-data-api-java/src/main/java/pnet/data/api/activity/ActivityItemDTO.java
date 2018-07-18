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
package pnet.data.api.activity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.brand.BrandLinkDTO;
import pnet.data.api.brand.WithBrandLinks;
import pnet.data.api.util.WithDescription;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a activity. This object contains only minimal information and is used as result of search operations and
 * reference items.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about an activity")
public class ActivityItemDTO
    implements WithMatchcode, WithLabel, WithDescription, WithTenants, WithBrandLinks, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = 156511831954172558L;

    @ApiModelProperty(notes = "The unique matchcode of the activity")
    private final String matchcode;
    @ApiModelProperty(notes = "The label of the activity in the requested language")
    private final String label;
    @ApiModelProperty(notes = "The description of the activity in the requested language")
    private final String description;
    @ApiModelProperty(notes = "The tenants where the activity is valid")
    private final Collection<String> tenants;
    @ApiModelProperty(notes = "The brands where the activity is valid")
    private final Collection<BrandLinkDTO> brands;
    @ApiModelProperty(notes = "The time and date when the activity was last changed")
    private final LocalDateTime lastUpdate;

    public ActivityItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("brands") Collection<BrandLinkDTO> brands, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = Objects.requireNonNull(matchcode, "Matchcode is null");
        this.label = Objects.requireNonNull(label, "Label is null");
        this.description = description;
        this.tenants = Collections.unmodifiableCollection(Objects.requireNonNull(tenants, "Tenants is null"));
        this.brands = Collections.unmodifiableCollection(Objects.requireNonNull(brands, "Brands is null"));
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    /**
     * @return The label in the requested language.
     */
    @Override
    public String getLabel()
    {
        return label;
    }

    /**
     * @return The description in the requested language.
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    @Override
    public Collection<BrandLinkDTO> getBrands()
    {
        return brands;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format(
            "ActivityItemDTO [matchcode=%s, label=%s, description=%s, tenants=%s, brands=%s, lastUpdate=%s]", matchcode,
            label, description, tenants, brands, lastUpdate);
    }

}