<template>
    <table class="table table-hover table-bordered table-striped">
        <thead class="thead-light">
        <tr>
            <th colspan="100%">
              {{ $t('list.memos_list') }}
            </th>
        </tr>
        <tr>
          <th>{{ $t('memo.number') }}</th>
          <th>{{ $t('memo.date') }}</th>
        </tr>
        </thead>
        <tbody>
        <MemoTitleRow v-for="m of memoList"
                      :key="m.id"
                      :memo="m"
                      @selectMemo="selectMemo"
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
  components: {MemoTitleRow},
        methods: {
            selectMemo(item) {
                this.$emit('selectMemo', item);
            },
        },
    }
</script>

<style scoped>

</style>