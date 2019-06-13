package org.tensorflow.lite.examples.classification;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME_RECIPE = "receta";
        public static final String COLUMN_NAME_RECIPE_NAME = "nombre";
        public static final String COLUMN_NAME_RECIPE_DESCRIPTION = "descripcion";
        public static final String COLUMN_NAME_RECIPE_TIME = "tiempo_de_preparacion";
        public static final String COLUMN_NAME_RECIPE_TYPE = "tipo_de_comida";

        public static final String TABLE_NAME_INGREDIENT = "ingrediente";
        public static final String COLUMN_NAME_INGREDIENT_NAME = "nombre";
        public static final String COLUMN_NAME_INGREDIENT_RECIPE = "receta";

        public static final String TABLE_NAME_STEPS = "preparacion";
        public static final String COLUMN_NAME_STEPS_STEP = "paso";
        public static final String COLUMN_NAME_STEPS_RECIPE = "receta";

        public static final String TABLE_NAME_RECIPE_INFO = "info_nutricional";
        public static final String COLUMN_NAME_RECIPE_INFO_RECIPE = "receta";
        public static final String COLUMN_NAME_RECIPE_INFO_SIZE = "porcion";
        public static final String COLUMN_NAME_RECIPE_INFO_CALORIES = "valor_energetico";
        public static final String COLUMN_NAME_RECIPE_INFO_CARBS = "carbohidratos";
        public static final String COLUMN_NAME_RECIPE_INFO_PROTS = "proteinas";
        public static final String COLUMN_NAME_RECIPE_INFO_FATS = "grasas_totales";
        public static final String COLUMN_NAME_RECIPE_INFO_FATS_SAT = "grasas_saturadas";
        public static final String COLUMN_NAME_RECIPE_INFO_FATS_TRANS = "grasas_trans";
        public static final String COLUMN_NAME_RECIPE_INFO_FIBER = "fibra_alimentaria";
        public static final String COLUMN_NAME_RECIPE_INFO_NA = "sodio";
        public static final String COLUMN_NAME_RECIPE_INFO_CA = "calcio";




        public static final String SQL_CREATE_RECIPE =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_RECIPE + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_RECIPE_NAME + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_DESCRIPTION + " TEXT," +
                        FeedEntry.COLUMN_NAME_RECIPE_TIME + " INTEGER," +
                        FeedEntry.COLUMN_NAME_RECIPE_TYPE + " VARCHAR" +
                        ")";

        public static final String SQL_CREATE_INGREDIENT =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_INGREDIENT + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_INGREDIENT_NAME + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_INGREDIENT_RECIPE + " VARCHAR" +
                        ")";

        public static final String SQL_CREATE_STEPS =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_STEPS + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_STEPS_STEP + " TEXT," +
                        FeedEntry.COLUMN_NAME_STEPS_RECIPE + " VARCHAR" +
                        ")";

        public static final String SQL_CREATE_RECIPE_INFO =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_RECIPE_INFO + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_RECIPE + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_SIZE + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_CALORIES + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_CARBS + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_PROTS + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_SAT + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_FATS_TRANS + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_FIBER + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_NA + " VARCHAR," +
                        FeedEntry.COLUMN_NAME_RECIPE_INFO_CA + " VARCHAR" +
                        ")";


        public static final String SQL_DELETE_RECIPE =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_RECIPE;
        public static final String SQL_DELETE_INGREDIENT =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_INGREDIENT;
        public static final String SQL_DELETE_STEPS =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_STEPS;
        public static final String SQL_DELETE_RECIPE_INFO =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_RECIPE_INFO;

        public static final String RECIPE_TSV = "recetas.tsv";
        public static final String INGREDIENT_TSV = "ingredientes.tsv";
        public static final String STEP_TSV = "pasos.tsv";
        public static final String RECIPE_INFO_TSV = "info_nutricional.tsv";


    }


}