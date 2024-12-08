package com.roxasjearom.spotifybootleg.ui.home.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
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
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.header_category),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow {
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
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onCategoryClicked(category.id) },
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.iconUrl)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .width(124.dp)
                    .height(124.dp)
            )
            Text(
                text = category.name.uppercase(),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(CenterHorizontally),
            )
        }
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
            onCategoryClicked = {}
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
