package traben.entity_model_features.models.vanilla_model_compat.model_wrappers.biped;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import traben.entity_model_features.mixin.accessor.ModelAccessor;
import traben.entity_model_features.mixin.accessor.entity.model.PlayerEntityModelAccessor;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFArmorableModel;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFCustomEntityModel;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFGenericCustomEntityModel;

import java.util.HashMap;

public class EMFCustomPlayerEntityModel<T extends LivingEntity> extends PlayerEntityModel<T> implements EMFCustomEntityModel<T>, EMFArmorableModel, ModelWithHat {

    public EMFGenericCustomEntityModel<T> getThisEMFModel() {
        return thisEMFModel;
    }

    public boolean doesThisModelNeedToBeReset() {
        return false;
    }

    private final EMFGenericCustomEntityModel<T> thisEMFModel;


    private static final HashMap<String,String> optifineMap = new HashMap<>(){{
        put("hat","headwear");
        put("cloak", "cape");
    }};
    public EMFCustomPlayerEntityModel(EMFGenericCustomEntityModel<T> model) {
        super( EMFCustomEntityModel.getFinalModelRootData(
                PlayerEntityModel.getTexturedModelData(Dilation.NONE,((PlayerEntityModelAccessor)model.vanillaModel).isThinArms()).getRoot().createPart(0,0),
                model, optifineMap),((PlayerEntityModelAccessor)model.vanillaModel).isThinArms());

        thisEMFModel=model;
        thisEMFModel.clearAllFakePartChildrenData();

        ((ModelAccessor)this).setLayerFactory(getThisEMFModel()::getLayer2);


    }




    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

            thisEMFModel.render(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    @Override
    public void setAngles(T livingEntity, float f, float g, float h, float i, float j) {

            thisEMFModel.child = child;
            thisEMFModel.sneaking = sneaking;
            thisEMFModel.riding = riding;
            thisEMFModel.handSwingProgress = handSwingProgress;
            thisEMFModel.setAngles(livingEntity, f, g, h, i, j);

    }

    @Override
    public void animateModel(T livingEntity, float f, float g, float h) {
        //super.animateModel(livingEntity, f, g, h);

            thisEMFModel.animateModel(livingEntity, f, g, h);

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
//        super.setArmAngle(arm, matrices);
////        if(thisEMFModel.vanillaModel instanceof PlayerEntityModel<?> model){
////            model.setArmAngle(arm, matrices);
////        }
//    }
}
