<template class="">
  <div class="w-75 m-auto">

    <div class="row m-2">
      <b-button class="col-2" @click="createDocument">
        {{ $t('message.button.create') }}
      </b-button>
      <div class="col-sm"/>
      <b-check class="col-auto my-auto ml-auto mr-0" :checked="IS_PAGEABLE" @change="viewIsPageable">
        {{ $t('message.page.is_paged') }}
      </b-check>
      <span class="col-auto my-auto text-right ">
          {{ $t('message.page.qty_view') }}
      </span>
      <b-input class="col-1 form-data" v-model="getInPage"/>
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
    <NicPagination
        v-if="PAGES > 1"
        :page="PAGE"
        :pages="PAGES"
        @selectPage="selectPage"
    />
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import DocumentListRow from "@/components/document/DocumentListRow";
import router from "../../router";
import NicPagination from "@/components/basictable/nic-pagination";

export default {
  name: "DocumentList",
  components: {
    NicPagination,
    DocumentListRow
  },
  data() {
    return {
      page: 1
    }
  },
  computed: {
    ...mapGetters([
      'DOCUMENTS',
      'PAGES',
      'PAGE',
      'IN_PAGE',
      'IS_PAGEABLE',
    ]),
    getInPage: {
      get() {
        return this.IN_PAGE
      },
      set(newValue) {
        this.SET_IN_PAGE(newValue);
        this.GET_ALL_DOCUMENTS_LIST({
          'currentPage': this.PAGE,
          'inPage': this.IN_PAGE,
          'isPageable': this.IS_PAGEABLE,
        });
      }
    }
  },
  methods: {
    ...mapActions([
      'GET_ALL_DOCUMENTS',
      'SET_IS_PAGEABLE',
      'SET_IN_PAGE',
      'GET_ALL_DOCUMENTS_LIST',
      'GET_DOCUMENTS_PAGE'
    ]),
    viewIsPageable(value) {
      this.SET_IS_PAGEABLE(value);
      this.GET_ALL_DOCUMENTS_LIST({
        'currentPage': this.PAGE,
        'inPage': this.IN_PAGE,
        'isPageable': this.IS_PAGEABLE,
      });
    },
    createDocument() {
      router.push('/planner/documents/0');
    },
    selectPage(page) {
      console.log("page from select " + page);
      this.page = page;
      let par = {
        "page": page,
        "offset": this.IN_PAGE,
      }
      this.GET_DOCUMENTS_PAGE(par);
    },
  },
  mounted() {
    this.GET_ALL_DOCUMENTS_LIST({
      'currentPage': this.PAGE,
      'inPage': this.IN_PAGE,
      'isPageable': this.IS_PAGEABLE,
    });
  }
}
</script>

<style scoped>

</style>