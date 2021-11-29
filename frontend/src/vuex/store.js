import Vue from 'vue'
import Vuex from 'vuex'
import appStore from "@/vuex/appStore";
import usersStore from "@/vuex/usersStore";
import rolesStore from "@/vuex/rolesStore";
import searchStore from "@/vuex/searchStore";
import titleListStore from "@/vuex/titleListStore";
import permStore from "@/vuex/permStore";
import departmentStore from "@/vuex/departmentStore";

Vue.use(Vuex);

let store = new Vuex.Store({
    modules: {
        appStore,
        usersStore,
        rolesStore,
        searchStore,
        titleListStore,
        permStore,
        departmentStore
    },
    state: {
        isModalShow: false,
        isModalShowSecond: false,
        isModalCreate: false,
        isModalEdit: false,
        isModalDelete: false,
        error: {},

        currentPage: 0,
        pages: 1,
        inPage: 5,
        isPageable: true
    },
    getters: {
        MODAL_SHOW: state => state.isModalShow,
        MODAL_SECOND_SHOW: state => state.isModalShowSecond,
        MODAL_CREATE: state => state.isModalCreate,
        MODAL_EDIT: state => state.isModalEdit,
        MODAL_DELETE: state => state.isModalDelete,
        GET_ERROR: state => state.error,

        PAGE: state => state.currentPage,
        PAGES: state => state.pages,
        IN_PAGE: state => state.inPage,
        IS_PAGEABLE: state => state.isPageable,
    },
    actions: {
        SET_IS_MODAL_CREATE: ({commit}, value) => {
            commit('SET_MODAL_CREATE_STATE', value);
        },
        SET_IS_MODAL_EDIT: ({commit}, value) => {
            commit('SET_MODAL_EDIT_STATE', value);
        },
        SET_IS_MODAL_DELETE: ({commit}, value) => {
            commit('SET_MODAL_EDIT_STATE', value);
        },
        SET_MODAL_STATE: ({commit}, value) => {
            commit('SET_MODAL_STATE', value);
        },
        SET_MODAL_SECOND_STATE: ({commit}, value) => {
            commit('SET_MODAL_SECOND_STATE', value);
        },
        SET_ERROR: ({commit}, value) => {
            commit('SET_ERROR', value);
        },
        ACTION_CLOSE_MODAL: ({commit}) => {
            commit('SET_MODAL_STATE', false);
            commit('SET_ERROR', {});
            commit('SET_MODAL_EDIT_STATE', false);
            commit('SET_MODAL_CREATE_STATE', false);
        },

        SET_IS_PAGEABLE: ({commit}, value) => {
            commit('SET_IS_PAGEABLE', value);
        },
        SET_IN_PAGE: ({commit}, value) => {
            commit('SET_IN_PAGE', value);
        },

    },
    mutations: {
        SET_MODAL_STATE: (state, value) => {
            state.isModalShow = value;
        },
        SET_MODAL_SECOND_STATE: (state, value) => {
            state.isModalShowSecond = value;
        },
        SET_MODAL_CREATE_STATE: (state, value) => {
            state.isModalCreate = value;
        },
        SET_MODAL_EDIT_STATE: (state, value) => {
            state.isModalEdit = value;
        },
        SET_MODAL_DELETE_STATE: (state, value) => {
            state.isModalDelete = value;
        },
        SET_ERROR: (state, value) => {
            state.error = value;
        },

        SET_PAGE: (state, result) => {
            state.currentPage = result;
        },
        SET_PAGES: (state, result) => {
            state.pages = result;
        },
        SET_IS_PAGEABLE: (state, result) => {
            state.isPageable = result;
        },
        SET_IN_PAGE: (state, result) => {
            state.inPage = result;
        },

    }

});

export default store;