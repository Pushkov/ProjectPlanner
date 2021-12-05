<template class=" mt-xl-5">
    <div class="w-75 m-auto">
        <div v-if="WORKSHOP !== undefined && WORKSHOP !== {}">

          <!--          <router-link :to="{ path: $store.state.route.from.fullPath }">-->
          <!--            Back-->
          <!--          </router-link>-->


          <table class="table table-hover table-bordered table-striped">
                <thead class="thead-light">
                <tr>
                    <th>! FIELD NAME</th>
                    <th>! FIELD VALUE</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <th>{{ $t('message.workshop.name') }}</th>
                  <td>{{ WORKSHOP.name }}</td>
                </tr>
                <tr>
                  <th>{{ $t('message.workshop.full_name') }}</th>
                  <td>{{ WORKSHOP.fullName }}</td>
                </tr>
                </tbody>
          </table>
          <b-button class="col-2 mt-xl-5" @click="test">
            <h5 class="m-auto">
              {{ $t('message.button.back') }}
            </h5>
          </b-button>
        </div>
        <div v-else>
            <h4 class="m-xl-5">{{$t('message.workshop.no_list')}}</h4>
        </div>
    </div>
</template>


<script>
import {mapActions, mapGetters} from 'vuex'
import router from "../../router";

export default {
  name: "WorkshopsSingle",
  data() {
    return {}
  },
  components: {},
  computed: {
    ...mapGetters([
      'WORKSHOP'
    ]),
  },
        methods: {
          ...mapActions([
            'GET_WORKSHOP'
          ]),
          test() {
            router.go(-1)
          },

        },
        mounted() {
            this.GET_WORKSHOP(this.$route.params.id)
        }

    }
</script>

<style scoped>

</style>