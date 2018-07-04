package pnet.data.api.util;

/**
 * Restricts the types of the advisor assignments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorType(String... advisorAssignmentTypeMatchcodes)
    {
        return restrict("advisorAssignmentType", (Object[]) advisorAssignmentTypeMatchcodes);
    }

}
