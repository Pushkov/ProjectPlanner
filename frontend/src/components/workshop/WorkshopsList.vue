<template class=" mt-xl-5">
    <div class="w-75 m-auto">
        <div v-if="WORKSHOPS.length > 0">
            <div class=" row text-left my-3">
                <b-button class="col-2" @click="createWorkshop">
                    <h5 class="m-auto">
                        {{$t('message.button.create')}}
                    </h5>
                </b-button>
                <div class="col-sm"></div>
                <b-check class="col-auto m-auto" :checked="isPageable" @change="viewIsPageable">
                    {{$t('message.page.is_paged')}}
                </b-check>
                <div class="col-auto m-auto row">
                    <span class="col-sm m-auto mr-0 text-right">
                     {{$t('message.page.qty_view')}}
                    </span>
                    <b-input class="col-2 mx-2 form-data" v-model="qtyInPage"/>
                </div>

                <table class="table table-hover table-bordered table-striped">
                    <thead class="thead-light">
                    <tr>
                        <th>Id</th>
                        <th>{{$t('message.workshop.name')}}</th>
                        <th>{{$t('message.workshop.full_name')}}</th>
                        <th>{{$t('message.workshop.index')}}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <WorkshopTableRow
                            v-for="ws of WORKSHOPS"
                            :item="ws"
                            :key="ws.id"
                            @selectWorkshop="selectWorkshop"
                    />
                    </tbody>
                </table>
                <nic-pagination
                        v-if="pages > 1"
                        :page="currentPage"
                        :pages="pages"
                        @selectPage="selectPage"
                />
            </div>


        </div>
        <div v-else>
            <h4 class="m-xl-5">{{$t('message.workshop.no_list')}}</h4>
        </div>
    </div>
</template>


<script>
import {mapActions, mapGetters} from 'vuex'
import WorkshopTableRow from "./WorkshopTableRow";
import router from "../../router";

export default {
  name: "WorkshopsList",
  data() {
    return {
      isPageable: true,
      qtyInPage: 5,
      pages: 1,
      currentPage: 0,
    }
  },
  components: {WorkshopTableRow},
        computed: {
            ...mapGetters([
                'WORKSHOPS'

            ]),
        },
        methods: {
            ...mapActions([
              'GET_ALL_WORKSHOPS'
            ]),
          viewIsPageable(value) {
            this.isPageable = value;
          },
          selectWorkshop(id) {
            router.push('/planner/workshops/' + id);
          },
          selectPage() {

          },
          createWorkshop() {

          },
        },
        mounted() {
            this.GET_ALL_WORKSHOPS();
        }

    }
</script>

<style scoped>

</style>