package traben.entity_model_features.models.vanilla_model_compat.model_wrappers.other;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import traben.entity_model_features.mixin.accessor.ModelAccessor;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFCustomEntityModel;
import traben.entity_model_features.models.vanilla_model_compat.model_wrappers.EMFGenericCustomEntityModel;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class EMFCustomSilverfishEntityModel<T extends LivingEntity> extends SilverfishEntityModel<T> implements EMFCustomEntityModel<T> {

    public EMFGenericCustomEntityModel<T> getThisEMFModel() {
        return thisEMFModel;
    }

    public boolean doesThisModelNeedToBeReset() {
        return false;
    }

    private final EMFGenericCustomEntityModel<T> thisEMFModel;

    private static final HashMap<String,String> optifineMap = new LinkedHashMap<>(){{
        put("segment0","body1");
        put("segment1","body2");
        put("segment2","body3");
        put("segment3","body4");
        put("segment4","body5");
        put("segment5","body6");
        put("segment6","body7");
        put("layer0","wing1");
        put("layer1","wing2");
        put("layer2","wing3");
    }};
    public EMFCustomSilverfishEntityModel(EMFGenericCustomEntityModel<T> model) {
        //super(QuadrupedEntityModel.getModelData(1,Dilation.NONE).getRoot().createPart(0,0));
        super( EMFCustomEntityModel.getFinalModelRootData(
                SilverfishEntityModel.getTexturedModelData().createModel(),
                model, optifineMap));

        thisEMFModel=model;
        ((ModelAccessor)this).setLayerFactory(getThisEMFModel()::getLayer2);
        thisEMFModel.clearAllFakePartChildrenData();

//        List<EMFModelPart> headCandidates = new ArrayList<>();

//        for (EMFModelPart part:
//                thisEMFModel.childrenMap.values()) {
//            if ("head".equals(part.selfModelData.part)) {
//                headCandidates.add(part);
//            }
//        }
//        //this is for mooshroom feature renderer
//        setNonEmptyPart(headCandidates,((QuadrupedEntityModelAccessor)this)::setHead);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

            thisEMFModel.render(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    @Override
    public void setAngles(T livingEntity, float f, float g, float h, float i, float j) {

            thisEMFModel.child = child;
            //thisEMFModel.sneaking = sneaking;
            thisEMFModel.riding = riding;
            thisEMFModel.handSwingProgress = handSwingProgress;
            thisEMFModel.setAngles(livingEntity, f, g, h, i, j);

    }

    @Override
    public void animateModel(T livingEntity, float f, float g, float h) {
        //super.animateModel(livingEntity, f, g, h);

            thisEMFModel.animateModel(livingEntity, f, g, h);

    }


}
