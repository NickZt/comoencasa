package org.tensorflow.lite.examples.classification;

public class Utils {

    //Add elements to ingredientlist and imageResouceIdList


    public static String[] ingredientList = {

            "aceituna",
            "acelga",
            "apio",
            "banana",
            "batata",
            "cebolla",
            "coco",
            "coll de brusela",
            "frutilla",
            "mango",
            "manzana roja",
            "manzana verde",
            "maracuya",
            "melon",
            "membrillo",
            "mora",
            "morron rojo",
            "naranja",
            "nuez",
            "palta",
            "pepino",
            "pera",
            "perejil",
            "pomelo",
            "puerro",
            "rabanito",
            "rabano",
            "remolacha",
            "repollo",
            "sandia",
            "tomate",
            "tomate cherry",
            "uva",
            "zanahoria",
            "zapallito",
            "zapallo"


    };

    public static int[] imageResouceIdSuggestedRecipesList = {
            R.drawable.recetassugeridasbrocoli,
            R.drawable.recetassugeridascoliflor,
            R.drawable.recetassugeridaszucchini
    };

    public static int[] imageResouceIdSelectionList = {
            R.drawable.seleccionsopadebrocoli,
            R.drawable.seleccionbocaditosdecoliflor,
            R.drawable.seleccionzucchinisrellenos
    };

    public static int[] imageResouceIdNuticionalInfoList = {
            R.drawable.informacionnutricionalsopadebrocoli,
            R.drawable.informacionnutricionalbocaditosdecoliflor,
            R.drawable.informacionnutricionalzucchinisrellenos
    };


}
