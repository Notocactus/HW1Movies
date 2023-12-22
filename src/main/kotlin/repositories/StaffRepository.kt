package repositories

import dao.JSONStuffSerializer
import entities.Staff
import staffFilePath

class StaffRepository {
    private val path =  staffFilePath
    private var staffArray: Array<Staff>? = JSONStuffSerializer().jsonDeserialize(path)
    fun enter(login: String, password: String): Boolean{
        if (staffArray != null){
            for (staff in staffArray!!){
                if (staff.login == login && password.hashCode() == staff.password){
                    return true
                }
            }
        }
        return false
    }

    fun register(login: String, password: String): Boolean{
        if (staffArray != null){
            for (staff in staffArray!!){
                if (staff.login == login || password.hashCode() == staff.password){
                    return false
                }
            }
            staffArray = staffArray!! + Staff(login, password.hashCode())
        }
        else{
            staffArray = arrayOf(Staff(login, password.hashCode()))
        }
        JSONStuffSerializer().jsonSerialize(path, staffArray!!)
        return true
    }
}