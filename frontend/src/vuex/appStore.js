const appStore = {
    state: {
        listMonths: [
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
        ALL_MONTHS: state => state.listMonths,
    },
    actions: {},
    mutations: {}
};

export default appStore;