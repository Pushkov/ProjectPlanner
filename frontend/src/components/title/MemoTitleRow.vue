<template>
    <tr @click="selectMemo">
        <td>
            {{memo.number}}
        </td>
        <td>
            {{memo.dateTime}}
        </td>
    </tr>
</template>

<script>

    export default {
        name: "MemoTitleRow",
        props: {
            memo: {
                type: Object,
                default: () => {
                    return {}
                }
            },
        },
        components: {},
        methods: {
            selectMemo() {
                this.$emit('selectMemo', this.memo);
            },
        },
    }
</script>

<style scoped>

</style>