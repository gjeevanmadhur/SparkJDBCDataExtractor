package utils

import scala.collection.mutable.Map

object PartitionColums {

  val partitionColums = Map("CUCPCPP" -> "CPALNB", "DSJLCPP" -> "JLALNB", "CCDKREP" -> "DKNANB",
    "CCEAREP" -> "EAPVNB", "CCDHREP" -> "DHMONB", "CRF3REP" -> "F3SEQ#", "CRJ7CPP" -> "J7ALNB","CCARCPP"->"ARALNB"
    , "DSFFCPP" -> "FFBYNB", "DSFECPP" -> "FEBYNB", "CCD2CPP" -> "D2ALNB")



  val mapdatabase = Map("CUCPCPP" -> "customer.db", "DSJLCPP" -> "customer.db", "CCDKREP" -> "customer.db", "CCEAREP" -> "customer.db"
    , "CCDHREP" -> "customer.db", "CCARCPP" -> "customer.db", "CRF3REP" -> "promotions.db", "CRJ7CPP" -> "customer.db", "DSFFCPP" -> "dmcredit.db", "DSFECPP" -> "dmcredit.db"
    , "DSA7CPP" -> "transactions.db", "CL620P" -> "dmcredit.db", "CCD2CPP" -> "customer.db")


  val maphadooptableName = Map("CUCPCPP" -> "customer_personal_detail_cucpcpp_nifi", "DSJLCPP" -> "customer_phone_numbers_dsjlcpp_nifi",
    "CCDKREP" -> "fraud_addresses_ccdkrep_nifi", "CCEAREP" -> "fraud_customer_name_ccearep_nifi", "CCDHREP" -> "fraud_soc_src_nbr_ccdhrep_nifi",
    "CCARCPP" -> "customer_insurance_ccarcpp_nifi", "CRF3REP" -> "promo_detail_crf3rep_nifi",
    "CRJ7CPP" -> "rtn_check_reference_crj7cpp_nifi",
    "DSFFCPP" -> "register_collections_zz_dsffcpp_nifi",
    "DSFECPP" -> "register_collections_zz_dsfecpp_nifi", "CCD2CPP" -> "credit_customer_ins_certificate_ccd2cpp_nifi")

}
