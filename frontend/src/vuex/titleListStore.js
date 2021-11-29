import {AXIOS} from "@/vuex/axios-export"

const titleListStore = {
    state: {
        titleList: {},
    },
    getters: {
        TITLE_LIST: state => state.titleList,
    },
    actions: {
        GET_TITLE_LIST: async ({commit}, year) => {
            await AXIOS.get('/titlelist/' + year)
                .then(responce => {
                    commit('SET_TITLE_LIST', responce.data);
                })
                .catch()
        }
    },
    mutations: {
        SET_TITLE_LIST: (state, result) => {
            state.titleList = result; // Или
        },
    }
};

export default titleListStore;