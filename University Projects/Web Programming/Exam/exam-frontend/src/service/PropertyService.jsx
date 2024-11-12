import axios from "axios";

class PropertyService {
    PropertyService(){

    }

    async getAllProperties(){
        try {
            const response = await axios.get('http://localhost:8080/api/property/getall');
            return response.data;
        } catch (error) {
            if (error.response) {
                // Server responded with a status code outside of 2xx
                throw error.response.data;
            } else if (error.request) {
                // Request was made but no response was received
                throw 'No response received from server';
            } else {
                // Something went wrong while setting up the request
                throw 'Error setting up the request';
            }
        }
    }

    async addPropertyToUser(username, propertyToAdd){
        try {
            const response = await axios.post('http://localhost:8080/api/user/'+ username + '/add-property', propertyToAdd);
            return response.data;
        } catch (error) {
            if (error.response) {
                // Server responded with a status code outside of 2xx
                throw error.response.data;
            } else if (error.request) {
                // Request was made but no response was received
                throw 'No response received from server';
            } else {
                // Something went wrong while setting up the request
                throw 'Error setting up the request';
            }
        }
    }

    async deleteProperty(propertyId){
        try {
            const response = await axios.delete('http://localhost:8080/api/property/' + propertyId + '/delete');
            return response.data;
        } catch (error) {
            if (error.response) {
                // Server responded with a status code outside of 2xx
                throw error.response.data;
            } else if (error.request) {
                // Request was made but no response was received
                throw 'No response received from server';
            } else {
                // Something went wrong while setting up the request
                throw 'Error setting up the request';
            }
        }
    }

}

export default new PropertyService()
