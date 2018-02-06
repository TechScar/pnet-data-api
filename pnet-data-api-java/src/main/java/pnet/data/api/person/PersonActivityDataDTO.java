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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithMatchcode;

/**
 * Holds the activity of a person for one company and brand.
 *
 * @author ham
 */
public class PersonActivityDataDTO implements WithMatchcode, Serializable
{

    private static final long serialVersionUID = 4247336068734009775L;

    private final String matchcode;
    private final boolean dueToFunction;

    public PersonActivityDataDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("dueToFunction") boolean dueToFunction)
    {
        super();

        this.matchcode = matchcode;
        this.dueToFunction = dueToFunction;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    public boolean isDueToFunction()
    {
        return dueToFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        PersonActivityDataDTO other = (PersonActivityDataDTO) obj;

        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [dueToFunction=%s]", matchcode, dueToFunction);
    }

}