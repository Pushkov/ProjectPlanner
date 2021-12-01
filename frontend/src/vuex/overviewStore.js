import {AXIOS} from "@/vuex/axios-export";

const overviewStore = {
    state: {
        items: [],
        item: {},
        months: [
            {name: 'За весь год', value: -1},
            {name: 'Январь', value: 0},
            {name: 'Февраль', value: 1},
            {name: 'Март', value: 2},
            {name: 'Апрель', value: 3},
            {name: 'Май', value: 4},
            {name: 'Июнь', value: 5},
            {name: 'Июль', value: 6},
            {name: 'Август', value: 7},
            {name: 'Сентябрь', value: 8},
            {name: 'Октябрь', value: 9},
            {name: 'Ноябрь', value: 10},
            {name: 'Декабрь', value: 11},
        ]
    },
    getters: {
        ITEMS: state => state.items,
        ITEM: state => state.item,
        MONTHS: state => state.months,
    },
    actions: {
        GET_ITEMS: async ({commit}) => {
            await AXIOS.get('/planprojects')
                .then(responce => {
                    commit('SET_ITEMS', responce.data);
                })
                .catch()
        },
        GET_ITEM: async ({commit}, id) => {
            await AXIOS.get('/planprojects/' + id)
                .then(responce => {
                    commit('SET_ITEM', responce.data);
                })
                .catch()
        },
    },
    mutations: {
        SET_ITEMS: (state, value) => {
            state.items = value;
        },
        SET_ITEM: (state, value) => {
            state.item = value;
        },
    }
};

export default overviewStore;