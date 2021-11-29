<template>
    <table class="table table-hover table-bordered table-striped">
        <thead class="thead-light">
        <tr>
            <th colspan="100%">
                Список служебных записок
            </th>
        </tr>
        <tr>
            <th>Номер с/з</th>
            <th>Дата с/з</th>
        </tr>
        </thead>
        <tbody>
        <MemoTitleRow v-for="m of memoList"
                      :key="m.id"
                      :memo="m"
        />
        </tbody>
    </table>
</template>

<script>

    import MemoTitleRow from "./MemoTitleRow";

    export default {
        name: "MemoTitleTable",
        props: {
            memoList: {
                type: Array,
                default: () => {
                    return []
                }
            },
        },
        components: {MemoTitleRow}
    }
</script>

<style scoped>

</style>