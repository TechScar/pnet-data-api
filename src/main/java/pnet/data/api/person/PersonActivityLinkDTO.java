package pnet.data.api.person;

import pnet.data.api.activity.ActivityMatchcode;

/**
 * Holds the activity of a person for one company and brand.
 *
 * @author ham
 */
public class PersonActivityLinkDTO
{

    private final ActivityMatchcode activityMatchcode;
    private final boolean dueToFunction;

    public PersonActivityLinkDTO(ActivityMatchcode activityMatchcode, boolean dueToFunction)
    {
        super();
        this.activityMatchcode = activityMatchcode;
        this.dueToFunction = dueToFunction;
    }

    public ActivityMatchcode getActivityMatchcode()
    {
        return activityMatchcode;
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
        result = prime * result + ((activityMatchcode == null) ? 0 : activityMatchcode.hashCode());
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
        PersonActivityLinkDTO other = (PersonActivityLinkDTO) obj;
        if (activityMatchcode == null)
        {
            if (other.activityMatchcode != null)
            {
                return false;
            }
        }
        else if (!activityMatchcode.equals(other.activityMatchcode))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [dueToFunction=%s]", activityMatchcode, dueToFunction);
    }

}
