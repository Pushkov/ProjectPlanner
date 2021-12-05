<template>
    <tr>
        <td>{{ item.id }}</td>
        <td>{{ item.year }}</td>
        <td>{{getMonth}}</td>
        <td>{{ item.projectName }}</td>
        <td>{{ item.projectDesignation }}</td>
        <td>{{ item.departmentName }}</td>
        <td>{{ item.taskName }}</td>
    </tr>
</template>

<script>
    import {mapGetters} from "vuex";

    export default {
        name: "OverviewListRow",
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
        computed: {
            ...mapGetters([
                'ALL_LOCALE_MONTHS',
            ]),
            getMonth() {
                // return  this.item.month;
                return this.ALL_LOCALE_MONTHS.filter(x => x.value === this.item.month)[0].name;
            },
        }
    }
</script>

<style scoped>

</style>