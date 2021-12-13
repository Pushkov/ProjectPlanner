import {AXIOS} from "@/vuex/axios-export"

const workshopStore = {
    state: {
        workshops: [],
        workshop: {},
    },
    getters: {
        WORKSHOPS: state => state.workshops,
        WORKSHOP: state => state.workshop,
    },
    actions: {
        GET_ALL_WORKSHOPS: async ({commit}) => {
            await AXIOS.get('/workshops')
                .then(responce => {
                    commit('SET_WORKSHOPS', responce.data);
                })
                .catch()
        },
        GET_WORKSHOP: async ({commit}, id) => {
            await AXIOS.get('/workshops/' + id)
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