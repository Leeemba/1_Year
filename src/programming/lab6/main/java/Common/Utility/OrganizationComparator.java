package Common.Utility;

import Common.Models.Organization;

import java.util.Comparator;

public class OrganizationComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return Integer.compare(o1.getINSTANCE_SIZE(), o2.getINSTANCE_SIZE());
    }
}
