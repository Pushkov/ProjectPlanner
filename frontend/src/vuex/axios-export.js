import axios from "axios";

export const AXIOS = axios.create({
    baseURL: `http://localhost:8080/planner/api/v1`
});

export const AXIOS_BASIC = axios.create({
    baseURL: `http://localhost:8080/`
});