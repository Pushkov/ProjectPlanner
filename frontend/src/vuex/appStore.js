import Vue from 'vue'

const appStore = {
    state: {
        appLocale: 'ru',

        listMonthsRU: [
            {name: 'за весь год', value: null},
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
        listMonthsEN: [
            {name: 'for the whole year', value: null},
            {name: 'January', value: 1},
            {name: 'February ', value: 2},
            {name: 'March ', value: 3},
            {name: 'April ', value: 4},
            {name: 'May ', value: 5},
            {name: 'June', value: 6},
            {name: 'July', value: 7},
            {name: 'August ', value: 8},
            {name: 'September ', value: 9},
            {name: 'September ', value: 10},
            {name: 'November ', value: 11},
            {name: 'December ', value: 12},
        ],

    },
    getters: {
        GET_LOCALE: state => state.appLocale,

        ALL_LOCALE_MONTHS: state => {
            if (state.appLocale === 'ru') {
                return state.listMonthsRU;
            } else if (state.appLocale === 'en') {
                return state.listMonthsEN;
            } else {
                return state.listMonthsRU;
            }
        },
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

