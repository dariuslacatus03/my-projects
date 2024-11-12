import axios from "axios";

class AuthenticationService {
    AuthenticationService(){

    }

    async login(username){
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {username});
            return response.data;
        } catch (error) {
            if (error.response) {
                // Server responded with a status code outside of 2xx
                throw error.response.data.message;
            } else if (error.request) {
                // Request was made but no response was received
                throw 'No response received from server';
            } else {
                // Something went wrong while setting up the request
                throw 'Error setting up the request';
            }
        }
    }

    async verifySecretAnswer(username, answer){
        try {
            const response = await axios.post('http://localhost:8080/api/auth/verifySecretAnswer', {username, answer});
            return response.data;
        } catch (error) {
            if (error.response) {
                // Server responded with a status code outside of 2xx
                throw error.response.data.message;
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

export default new AuthenticationService()