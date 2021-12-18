// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///planner/api/v1/
const searchStore = {
    state: {
        searchResult: {},
        execTime: -1,
        error_search_message: 'ниудачко'
    },
    getters: {
        SEARCH_RESULT: state => state.searchResult,
        EXECUTION_TIME: state => state.execTime,
        ERROR_SEARCH_LOADING_MESSAGE: state => state.error_search_message,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_SEARCH_RESULT: async ({commit, getters}, param) => {

            await AXIOS.get('/search',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_SEARCH_RESULT', responce.data);
                    commit('SET_EXEC_TIME', responce);
                })
                .catch(error => {
                        commit('SET_ERROR_LIST_LOADING_MESSAGE', error.message)
                    }
                )
        }
    },
    mutations: {
        SET_SEARCH_RESULT: (state, result) => {
            state.searchResult = result; // Или
            state.error_list_loading_message = '';
        },
        SET_EXEC_TIME: (state, resp) => {
            state.execTime = -1;
            if (resp.headers['logtime'] !== undefined)
                state.execTime = resp.headers['logtime'];
        },
        SET_SEARCH_LOADING_MESSAGE: (state, msg) => {
            state.error_list_loading_message = msg;
        }
    }
};

export default searchStore;