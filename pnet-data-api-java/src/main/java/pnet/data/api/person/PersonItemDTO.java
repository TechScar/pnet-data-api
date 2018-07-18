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
package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithPersonId;

/**
 * An result item for a search for persons.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a person")
public class PersonItemDTO implements WithPersonId, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -481025382258675738L;

    @ApiModelProperty(notes = "The unique id of the person")
    private final Integer personId;
    @ApiModelProperty(notes = "The tenant, in which this person is administrated")
    private final String administrativeTenant;
    @ApiModelProperty(notes = "The tenants where the person is valid")
    private final Collection<String> tenants;
    @ApiModelProperty(notes = "The form of the adress the person prefers")
    private final FormOfAddress formOfAddress;
    @ApiModelProperty(notes = "The academic title of the person")
    private final String academicTitle;
    @ApiModelProperty(notes = "The first name of the person")
    private final String firstName;
    @ApiModelProperty(notes = "The last name of the person")
    private final String lastName;
    @ApiModelProperty(notes = "The time and date when the person was last changed")
    private final LocalDateTime lastUpdate;

    public PersonItemDTO(@JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") String administrativeTenant,
        @JsonProperty("tenants") Collection<String> tenants, @JsonProperty("formOfAddress") FormOfAddress formOfAddress,
        @JsonProperty("academicTitle") String academicTitle, @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.tenants = tenants;
        this.formOfAddress = formOfAddress;
        this.academicTitle = academicTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Integer getPersonId()
    {
        return personId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public Collection<String> getTenants()
    {
        return tenants;
    }

    public FormOfAddress getFormOfAddress()
    {
        return formOfAddress;
    }

    public String getAcademicTitle()
    {
        return academicTitle;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

}