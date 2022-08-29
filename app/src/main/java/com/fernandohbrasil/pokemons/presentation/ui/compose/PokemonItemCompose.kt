package com.fernandohbrasil.pokemons.presentation.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fernandohbrasil.pokemons.R
import com.fernandohbrasil.pokemons.domain.model.PokemonItem
import com.fernandohbrasil.pokemons.presentation.viewmodel.ListItemViewModel
import org.koin.androidx.compose.get

private const val IMAGE_AVATAR = "image_avatar"
private const val TEXT_NAME = "text_name"
private const val TEXT_TYPES = "text_types"
private const val TEXT_ORDER = "text_order"
private const val TEXT_WEIGHT = "text_weight"
private const val TEXT_HEIGHT = "text_height"

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PokemonItemCompose(pokemonItem: PokemonItem) {
    val viewModel = get<ListItemViewModel>()
    viewModel.setModel(pokemonItem)
    val pokemon = viewModel.pokemonLiveData.observeAsState().value

    val constrains = ConstraintSet {
        val imageAvatar = createRefFor(IMAGE_AVATAR)
        val textName = createRefFor(TEXT_NAME)
        val textTypes = createRefFor(TEXT_TYPES)
        val textOrder = createRefFor(TEXT_ORDER)
        val textWeight = createRefFor(TEXT_WEIGHT)
        val textHeight = createRefFor(TEXT_HEIGHT)

        constrain(imageAvatar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(90.dp)
            height = Dimension.value(90.dp)
        }

        constrain(textName) {
            top.linkTo(imageAvatar.top)
            start.linkTo(imageAvatar.end, margin = 12.dp)
            end.linkTo(textOrder.start, margin = 6.dp)
            width = Dimension.fillToConstraints
        }

        constrain(textOrder) {
            top.linkTo(imageAvatar.top)
            start.linkTo(textName.end)
            end.linkTo(parent.end)
        }

        constrain(textTypes) {
            top.linkTo(textName.bottom)
            start.linkTo(textName.start)
            end.linkTo(textName.end)
            width = Dimension.fillToConstraints
        }

        constrain(textWeight) {
            bottom.linkTo(imageAvatar.bottom)
            start.linkTo(imageAvatar.end, margin = 12.dp)
        }

        constrain(textHeight) {
            bottom.linkTo(imageAvatar.bottom)
            end.linkTo(parent.end)
        }
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Surface {
            ConstraintLayout(
                constrains,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, Color.Black))
                        .layoutId(IMAGE_AVATAR),
                    painter = rememberImagePainter(
                        data = pokemon?.url,
                        builder = {
                            scale(coil.size.Scale.FILL)
                            placeholder(R.drawable.ic_loading)
                        }
                    ),
                    contentDescription = pokemon?.name
                )

                Text(
                    modifier = Modifier.layoutId(TEXT_NAME),
                    text = pokemon?.name.orEmpty(),
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    modifier = Modifier.layoutId(TEXT_ORDER),
                    text = pokemon?.order.orEmpty(),
                    fontSize = 14.sp,
                )

                Text(
                    modifier = Modifier.layoutId(TEXT_TYPES),
                    text = pokemon?.types.orEmpty(),
                    fontSize = 12.sp,
                )

                Text(
                    modifier = Modifier.layoutId(TEXT_WEIGHT),
                    text = pokemon?.weight.orEmpty(),
                    fontSize = 16.sp,
                )

                Text(
                    modifier = Modifier.layoutId(TEXT_HEIGHT),
                    text = pokemon?.weight.orEmpty(),
                    fontSize = 16.sp,
                )
            }
        }
    }
}