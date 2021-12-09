import {AXIOS} from "@/vuex/axios-export"

const titleListStore = {
    state: {

        titleLists: [],
        titleList: {},
    },
    getters: {
        TITLE_LIST: state => state.titleList,
        TITLE_LISTS: state => state.titleLists,
    },
    actions: {
        GET_TITLE_LIST: async ({commit}, year) => {
            if (year === undefined || year < 0) {
                await AXIOS.get('/titlelists')
                    .then(responce => {
                        commit('SET_TITLE_LISTS', responce.data);
                    })
                    .catch()
            } else {
                await AXIOS.get('/titlelists/' + year)
                    .then(responce => {
                        commit('SET_TITLE_LIST', responce.data);
                        commit('SET_TITLE_LISTS', []);
                    })
                    .catch()
            }
        }
    },
    mutations: {
        SET_TITLE_LISTS: (state, result) => {
            state.titleLists = result; // Или
        },
        SET_TITLE_LIST: (state, result) => {
            state.titleList = result; // Или
        },
    }
};

export default titleListStore;