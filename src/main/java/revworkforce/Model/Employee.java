package revworkforce.Model;

public class Employee {

        private String empId;
        private String name;
        private String email;
        private String phone;
        private String address;
        private String department;
        private String designation;
        private String password;
        private String role;
        private String managerId;
        public int isActive;

        public Employee() {}

        public Employee(String empId, String name, String email, String role) {
            this.empId = empId;
            this.name = name;
            this.email = email;
            this.role = role;
        }


        public String getEmpId() {

                return empId;
        }
        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhone() {
                return phone;
        }
        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getAddress() {
                return address;
        }
        public void setAddress(String address) {
                this.address = address;
        }

        public String getDepartment() {
                return department;
        }
        public void setDepartment(String department) {
                this.department = department;
        }

        public String getDesignation() {
                return designation;
        }
        public void setDesignation(String designation) {
                this.designation = designation;
        }

        public String getPassword() {
                return password;
        }
        public void setPassword(String password) {
                this.password = password;
        }

        public String getRole() {
                return role;
        }
        public void setRole(String role) {
                this.role = role;
        }

        public String getManagerId() {
                return managerId;
        }
        public void setManagerId(String managerId) {
                this.managerId = managerId;
        }

        public int getIsActive() {
                return isActive;
        }
        public void setIsActive(int isActive) {
                this.isActive = isActive;
        }
}
