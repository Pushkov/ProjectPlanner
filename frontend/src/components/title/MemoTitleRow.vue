<template>
    <tr>
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
        components: {}
    }
</script>

<style scoped>

</style>