import axios, { AxiosError } from 'axios';

class ExampleService {
    ExampleService(){

    }
    // *********************************************
    //
    //          GET SOMETHING EXAMPLE
    //
    // *********************************************
    async getSomethingExample(){
        try {
            const response = await axios.get('http://localhost:backendPort/backendPath');
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

    // *********************************************
    //
    //        GET SOMETHING BY ID EXAMPLE
    //
    // *********************************************
    async getSomethingByIdExample(objectId){
        try {
            const response = await axios.get('http://localhost:backendPort/backendPath' + objectId);
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

    // *********************************************
    //
    //          POST SOMETHING EXAMPLE
    //
    // *********************************************
    async postExample(objectToAdd){
        try {
            const response = await axios.post('http://localhost:backendPort/backendPath', objectToAdd);
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

    // *********************************************
    //
    //          UPDATE SOMETHING EXAMPLE
    //
    // *********************************************
    async updateExample(objectToUpdate){
        try {
            const response = await axios.put('http://localhost:backendPort/backendPath' + objectToUpdate.id, objectToUpdate);
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

    // *********************************************
    //
    //          DELETE SOMETHING EXAMPLE
    //
    // *********************************************
    async deleteExample(objectToDeleteId){
        try {
            const response = await axios.delete('http://localhost:backendPort/backendPath' + objectToDeleteId);
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
export default new ExampleService()