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
        error: {}
    },
    getters: {
        MODAL_SHOW: state => state.isModalShow,
        MODAL_SECOND_SHOW: state => state.isModalShowSecond,
        MODAL_CREATE: state => state.isModalCreate,
        MODAL_EDIT: state => state.isModalEdit,
        MODAL_DELETE: state => state.isModalDelete,
        GET_ERROR: state => state.error,
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
    }

});

export default store;