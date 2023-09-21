import axios from 'axios'

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees";

const EMPLOYE_ID = 8;

class EmployeeService{

    getEmployee(){
       return axios.get(EMPLOYEE_SERVICE_BASE_URL + '/' + EMPLOYE_ID);
    }
}

export default new EmployeeService