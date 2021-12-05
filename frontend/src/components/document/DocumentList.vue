<template class="">
  <div class="w-75 m-auto">

    <div class="row m-2">
      <b-button class="col-2">
        {{ $t('message.button.create') }}
      </b-button>
      <div class="col-sm"/>
      <b-check class="col-auto my-auto ml-auto mr-0" :checked="isPaged" @change="viewIsPageable">
        {{ $t('message.page.is_paged') }}
      </b-check>
      <span class="col-auto my-auto text-right ">
          {{ $t('message.page.qty_view') }}
      </span>
      <b-input class="col-1 form-data" v-model="inPage"/>
    </div>


    <table class="table table-hover table-bordered table-striped">
      <thead class="thead-light">
      <tr>
        <th>{{ $t('list.npp') }}</th>
        <th>{{ $t('document.designation') }}</th>
        <th>{{ $t('document.name') }}</th>
      </tr>
      </thead>
      <tbody>
      <DocumentListRow v-for="(doc, index) of DOCUMENTS"
                       :key="doc.id"
                       :document="doc"
                       :index="index + 1"
      />
      </tbody>
    </table>


  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import DocumentListRow from "@/components/document/DocumentListRow";

export default {
  name: "DocumentList",
  components: {DocumentListRow},
  data() {
    return {
      isPaged: true,
      inPage: 5,
    }
  },
  computed: {
    ...mapGetters([
      'DOCUMENTS'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_DOCUMENTS'
    ]),
    viewIsPageable() {
      this.inPaged = !this.isPaged;
    }
  },
  mounted() {
    this.GET_ALL_DOCUMENTS();
  }
}
</script>

<style scoped>

</style>