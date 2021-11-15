<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <table class="table table-hover table-bordered table-striped">
            <thead class="thead-light">
            <tr>
                <th>Номер ТЗ</th>
                <th>Название</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <table class="table table-hover table-bordered table-striped">
            <thead class="thead-light">
            <tr>
                <th>Номер с/з</th>
                <th>Название</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
            </tr>

            </tbody>
        </table>


    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "TitleListMain",
        data() {
            return {}
        },
        components: {},
        computed: {
            ...mapGetters([])
        },
        methods: {
            ...mapActions([]),
        },
        mounted() {
        }
    }
</script>

<style scoped>

</style>