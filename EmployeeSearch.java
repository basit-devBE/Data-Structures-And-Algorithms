public class EmployeeSearch {

    class Department{
        String name;
        int[] interns;
        int[] managers;
        Department[] subDepartments;
    }

   
    public boolean Search(Department department, int target) {
        for (int intern : department.interns) {
            if (intern == target){
                return true;
            }
        }
        
        int low = 0;
        int high = department.managers.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(department.managers[mid] == target){
                return true;
            } else if(department.managers[mid] < target){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }
        
        for(Department subDepartment : department.subDepartments){
            if(Search(subDepartment, target)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        EmployeeSearch es = new EmployeeSearch();
        
        Department techSupport = es.new Department();
        techSupport.name = "Tech Support";
        techSupport.interns = new int[]{88, 45, 12};  // Target 88 is here!
        techSupport.managers = new int[]{850, 900, 950};
        techSupport.subDepartments = new Department[0];
        
        Department webDev = es.new Department();
        webDev.name = "Web Development";
        webDev.interns = new int[]{33, 77};
        webDev.managers = new int[]{800, 810, 820};
        webDev.subDepartments = new Department[0];
        
        Department tech = es.new Department();
        tech.name = "Tech";
        tech.interns = new int[]{99};
        tech.managers = new int[]{700, 750, 800};
        tech.subDepartments = new Department[]{techSupport, webDev};
        
        Department marketing = es.new Department();
        marketing.name = "Marketing";
        marketing.interns = new int[]{55, 12};
        marketing.managers = new int[]{601, 602, 603};
        marketing.subDepartments = new Department[0];
        
        Department headOffice = es.new Department();
        headOffice.name = "Head Office";
        headOffice.interns = new int[]{902, 14, 7};
        headOffice.managers = new int[]{100, 200, 300, 400, 500};
        headOffice.subDepartments = new Department[]{marketing, tech};
        
        System.out.println("Searching for Employee ID 88...");
        boolean found88 = es.Search(headOffice, 88);
        System.out.println("Employee 88 found: " + found88);
        
        System.out.println("\nSearching for Employee ID 500...");
        boolean found500 = es.Search(headOffice, 500);
        System.out.println("Employee 500 found: " + found500);
        
        System.out.println("\nSearching for Employee ID 999...");
        boolean found999 = es.Search(headOffice, 999);
        System.out.println("Employee 999 found: " + found999);
    }



}