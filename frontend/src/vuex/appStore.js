import Vue from 'vue'

const appStore = {
    state: {
        appLocale: '',

        listMonths: [
            {name: 'За весь год', value: null},
            {name: 'Январь', value: 1},
            {name: 'Февраль', value: 2},
            {name: 'Март', value: 3},
            {name: 'Апрель', value: 4},
            {name: 'Май', value: 5},
            {name: 'Июнь', value: 6},
            {name: 'Июль', value: 7},
            {name: 'Август', value: 8},
            {name: 'Сентябрь', value: 9},
            {name: 'Октябрь', value: 10},
            {name: 'Ноябрь', value: 11},
            {name: 'Декабрь', value: 12},
        ],

    },
    getters: {
        GET_LOCALE: state => state.appLocale,

        ALL_LOCALE_MONTHS: state => state.listMonths,
    },
    actions: {
        SET_APPLICATION_LOCALE: ({commit}, loc) => {
            commit('SET_LOCALE', loc);
        }
    },
    mutations: {
        SET_LOCALE: (state, loc) => {
            state.appLocale = loc;
            Vue.i18n.set(state.appLocale);
        }

    }
};

export default appStore;

