package com.roxasjearom.spotifybootleg.ui.home.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.spotifybootleg.R
import com.roxasjearom.spotifybootleg.domain.model.Category
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme

@Composable
fun CategorySection(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryClicked: (String) -> Unit,
    onShowAllClicked: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.header_category),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = onShowAllClicked,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_show_all),
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxWidth(),
        ) {
            items(categories) { category ->
                CategoryItem(category = category, onCategoryClicked = onCategoryClicked)
            }
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onCategoryClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onCategoryClicked(category.id) }
            .padding(all = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.iconUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(CenterVertically),
        )
    }
}

@Preview
@Composable
fun CategorySectionPreview(modifier: Modifier = Modifier) {
    SpotifyBootlegTheme {
        CategorySection(
            categories = listOf(
                Category(
                    id = "0JQ5DAqbMKFQ00XGBls6ym",
                    name = "Hip-Hop",
                    iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
                ),
                Category(
                    id = "0JQ5DAqbMKFQ00XGBls6ym",
                    name = "K-pop",
                    iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
                ),
                Category(
                    id = "0JQ5DAqbMKFQ00XGBls6ym",
                    name = "Classical",
                    iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
                )
            ),
            onCategoryClicked = {},
            onShowAllClicked = {},
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview(modifier: Modifier = Modifier) {
    SpotifyBootlegTheme {
        CategoryItem(
            category = Category(
                id = "0JQ5DAqbMKFQ00XGBls6ym",
                name = "Hip-Hop",
                iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
            ),
            onCategoryClicked = {}
        )
    }
}
