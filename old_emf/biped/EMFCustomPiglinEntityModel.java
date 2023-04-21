package traben.entity_model_features.models.vanilla_model_compat.model_wrappers.biped;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import traben.entity_model_features.mixin.accessor.ModelAccessor;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFArmorableModel;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFCustomEntityModel;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFGenericCustomEntityModel;

import java.util.HashMap;

public class EMFCustomPiglinEntityModel<T extends LivingEntity> extends PiglinEntityModel<MobEntity> implements EMFCustomEntityModel<T>, EMFArmorableModel, ModelWithHat {

    public EMFGenericCustomEntityModel<T> getThisEMFModel() {
        return thisEMFModel;
    }

    public boolean doesThisModelNeedToBeReset() {
        return false;
    }

    private final EMFGenericCustomEntityModel<T> thisEMFModel;


    private static final HashMap<String,String> optifineMap = new HashMap<>(){{
        put("hat","headwear");
    }};
    public EMFCustomPiglinEntityModel(EMFGenericCustomEntityModel<T> model) {
        super( EMFCustomEntityModel.getFinalModelRootData(
                PiglinEntityModel.getModelData(new Dilation(0)).getRoot().createPart(0,0),
                model, optifineMap));

        thisEMFModel=model;
        thisEMFModel.clearAllFakePartChildrenData();

        ((ModelAccessor)this).setLayerFactory(getThisEMFModel()::getLayer2);


    }




    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

            thisEMFModel.render(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    @Override
    public void setAngles(MobEntity livingEntity, float f, float g, float h, float i, float j) {

            thisEMFModel.child = child;
            thisEMFModel.sneaking = sneaking;
            thisEMFModel.riding = riding;
            thisEMFModel.handSwingProgress = handSwingProgress;
            thisEMFModel.setAngles((T) livingEntity, f, g, h, i, j);

    }

    @Override
    public void animateModel(MobEntity livingEntity, float f, float g, float h) {
        //super.animateModel(livingEntity, f, g, h);

            thisEMFModel.animateModel((T) livingEntity, f, g, h);

    }




    @Override
    public EMFGenericCustomEntityModel<?> getArmourModel(boolean getInner) {
        return thisEMFModel.getArmourModel(getInner);
    }

    @Override
    public void setHatVisible(boolean visible) {
        thisEMFModel.setHatVisible(visible);
    }

//    @Override
//    public void setArmAngle(Arm arm, MatrixStack matrices) {
//        //super.setArmAngle(arm, matrices);
//        if(thisEMFModel.vanillaModel instanceof PiglinEntityModel<?> model){
//            model.setArmAngle(arm, matrices);
//        }
//    }


    @Override
    public void animateArms(MobEntity mobEntity, float f) {
        if(thisEMFModel.vanillaModel instanceof PiglinEntityModel ){
            ((PiglinEntityModel<MobEntity>)thisEMFModel.vanillaModel).animateArms( mobEntity, f);
        }
    }


}
