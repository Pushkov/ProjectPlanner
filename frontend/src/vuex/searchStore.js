// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///planner/api/v1/
const searchStore = {
    state: {
        searchResult: {},
        error_search_message: 'ниудачко'
    },
    getters: {
        SEARCH_RESULT: state => state.searchResult,
        ERROR_SEARCH_LOADING_MESSAGE: state => state.error_search_message,
    },
    actions: {
        GET_SEARCH_RESULT: async ({commit}, param) => {
            await AXIOS.get('/search', {
                params: param
            })
                .then(responce => {
                    commit('SET_SEARCH_RESULT', responce.data);
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
        SET_SEARCH_LOADING_MESSAGE: (state, msg) => {
            state.error_list_loading_message = msg;
        }
    }
};

export default searchStore;