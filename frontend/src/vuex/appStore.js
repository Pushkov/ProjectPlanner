import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export";
import {AUTH_ERROR} from "@/vuex/actions/auth";

const appStore = {
    state: {
        appLocale: localStorage.getItem('user-locale') || 'en',

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
        isAuth: false
    },
    getters: {
        IS_AUTH: state => state.isAuth,
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
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        CHECK_AUTH: ({commit, getters}) => {
            AXIOS.post('/auth/check', {},
                {
                    headers: {
                        'Authorization': getters.user_token
                    }
                }).then((resp) => {
                commit('SET_AUTH', resp.data)
            })
        },


        SET_USER_LOCALE: ({getters, dispatch}, loc) => {
            AXIOS.put('/engineers/' + getters.getUserName + '/locale', {},
                {
                    params: {
                        locale: loc
                    },
                    headers: {
                        'Authorization': getters.user_token
                    }
                }).then()
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        SET_APPLICATION_LOCALE: ({commit}, loc) => {
            commit('SET_LOCALE', loc);
        }
    },
    mutations: {
        SET_AUTH: (state, loc) => {
            state.isAuth = loc;
        },

        SET_LOCALE: (state, loc) => {
            state.appLocale = loc;
            localStorage.setItem('user-locale', loc);
            Vue.i18n.set(state.appLocale);
        }
    }
};

export default appStore;

