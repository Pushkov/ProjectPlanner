import {AXIOS} from "@/vuex/axios-export"

const workshopStore = {
    state: {
        workshops: [],
        workshop: {},
    },
    getters: {
        WORKSHOPS: state => state.workshops,
        WORKSHOP: state => state.workshop,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_WORKSHOPS: async ({commit, getters}) => {
            await AXIOS.get('/workshops',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_WORKSHOPS', responce.data);
                })
                .catch()
        },
        GET_WORKSHOP: async ({commit, getters}, id) => {
            await AXIOS.get('/workshops/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_WORKSHOP', responce.data);
                })
                .catch()
        },
    },
    mutations: {
        SET_WORKSHOPS: (state, value) => {
            state.workshops = value;
        },
        SET_WORKSHOP: (state, value) => {
            state.workshop = value;
        },
    }
};

export default workshopStore;